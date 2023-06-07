import { ReactNode, useEffect, useState } from 'react';
import { Invoice, InvoiceDetails, fetchInvoiceDetailsByInvoiceId, fetchInvoices } from '../services/fetchData';
import Card from './InvoiceCard';

interface ModalInvoiceDettailsProps {
	isOpen: boolean;
	onClose: () => void;
	invoiceId: Number | undefined;
	children: ReactNode;
}

export function ModalInvoiceDetails({ isOpen, onClose, invoiceId: invoiceId, children }: ModalInvoiceDettailsProps) {
	const [invoiceDetails, setInvoiceDetails] = useState<InvoiceDetails[]>([]);

	useEffect(() => {
		async function getData() {
			console.log('invoiceId', invoiceId);
			if (invoiceId === undefined) return;
			const result = await fetchInvoiceDetailsByInvoiceId(invoiceId);
			setInvoiceDetails(result);
		}
		getData();
	}, [invoiceId]);

	if (!isOpen) {
		return null;
	}

	return (
		<div className="fixed z-50 inset-0 overflow-y-auto">
			<div className="flex items-center justify-center min-h-screen">
				<div className="fixed inset-0 bg-gray-900 bg-opacity-75"></div>
				<div className="relative bg-gray-800 rounded-lg w-1/2 text-white">
					<button
						className="absolute top-0 right-0 m-4 text-gray-300 hover:text-gray-100"
						onClick={onClose}
					>
						X
					</button>

					<div className="p-6">{children}</div>

					{invoiceDetails.map((invoiceDeails: InvoiceDetails) => (
						
						<Card 						
							key={invoice.id}
							title={invoice.id}
						/>
					))}
				</div>
			</div>
		</div>
	);
}
