import React from 'react';
import styled from 'styled-components';

interface Invoice {
  title: string;
  total: string;
  createdAt: string;
}

interface CardProps {
  invoice: Invoice;
}

const Card = styled.div`
  background-color: black;
  color: white;
  padding: 16px;
  border-radius: 8px;
`;

const Title = styled.h2`
  font-size: 24px;
  margin-bottom: 8px;
`;

const Total = styled.p`
  font-size: 18px;
  margin-bottom: 8px;
`;

const CreatedAt = styled.p`
  font-size: 14px;
`;

const InvoiceCard: React.FC<CardProps> = ({ invoice }) => {
  return (
    <Card className='mt-2'>
      <Title>{invoice.title}</Title>
      <Total>Total: {invoice.total}</Total>
      <CreatedAt>Created at: {invoice.createdAt}</CreatedAt>
    </Card>
  );
};

export default InvoiceCard;	